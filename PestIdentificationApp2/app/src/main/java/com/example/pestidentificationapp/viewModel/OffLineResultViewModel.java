package com.example.pestidentificationapp.viewModel;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.pestidentificationapp.model.HistoryIdentificationResult;
import com.example.pestidentificationapp.other.Util;

import org.pytorch.IValue;
import org.pytorch.LiteModuleLoader;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class OffLineResultViewModel extends AndroidViewModel {


    public OffLineResultViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 离线预测
     * 增加多线程 处理较耗时
     *
     * @param fileName 文件名
     */
    public MutableLiveData<HistoryIdentificationResult> predict(final String fileName) throws ExecutionException, InterruptedException {

        FutureTask<MutableLiveData<HistoryIdentificationResult>> task =
                new FutureTask<>(new Callable<MutableLiveData<HistoryIdentificationResult>>() {
                    @Override
                    public MutableLiveData<HistoryIdentificationResult> call() {
                        MutableLiveData<HistoryIdentificationResult> result = new MutableLiveData<>();
                        Bitmap bitmap = null;
                        Module module = null;
                        try {
                            bitmap = Util.getDecodeFileBitmap(fileName);
                            module = LiteModuleLoader.load(assetFilePath("resnet50_102improve12.pt"));
                        } catch (IOException e) {
                            Log.e("Pytorch", "Error reading assets", e);
                        }
                        Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(bitmap,
                                TensorImageUtils.TORCHVISION_NORM_MEAN_RGB,
                                TensorImageUtils.TORCHVISION_NORM_STD_RGB);
                        Tensor outputTensor = module.forward(IValue.from(inputTensor)).toTensor();
                        float[] scores = outputTensor.getDataAsFloatArray();
                        Log.d("mytorch", "predict: 数组大小" + scores.length);
                        float maxScore = -Float.MAX_VALUE;
                        int maxScoreIdx = -1;
                        for (int i = 0; i < scores.length; i++) {
                            if (scores[i] > maxScore) {
                                maxScore = scores[i];
                                maxScoreIdx = i;
                            }
                            Log.d("mytorch", "predict: " + scores[i]);
                        }
                        HistoryIdentificationResult historyIdentificationResult = new HistoryIdentificationResult(
                                Util.getOfflineName()[maxScoreIdx],
                                Util.getOfflineLatinName()[maxScoreIdx]);
                        result.postValue(historyIdentificationResult);
                        return result;
                    }
                });
        new Thread(task).start();
        MutableLiveData<HistoryIdentificationResult> resultMutableLiveData = task.get();
        return resultMutableLiveData;
    }

    /**
     * Copies specified asset to the file in /files app directory and returns this file absolute path.
     *
     * @param assetName 模型文件名
     * @return absolute file path
     * @throws IOException
     */
    private String assetFilePath(String assetName) throws IOException {
        File file = new File(getApplication().getFilesDir(), assetName);
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        }
        try (InputStream is = getApplication().getAssets().open(assetName)) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buffer = new byte[4 * 1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
            }
            return file.getAbsolutePath();
        }
    }
}

