package com.example.pestidentificationapp.viewModel;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;

import com.example.pestidentificationapp.model.Pest;
import com.example.pestidentificationapp.other.OnRecyclerItemClickListener;

public class PestLibraryViewModel extends ViewModel {

    private ObservableList<Pest> pestObservableList = new ObservableArrayList<>();
    private Pest clickedPest;
    private OnRecyclerItemClickListener libraryItemClickListener = new OnRecyclerItemClickListener() {
        @Override
        public void onRecyclerItemClick(Object item) {
            clickedPest = (Pest) item;
        }
    };

    public PestLibraryViewModel() {
        for (int i = 0; i < 10; i++) {
            Pest pest = new Pest();
            pest.setName("黑蚱蝉");
            pest.setOrder("半翅目");
            pest.setGenus("蝉科");
            pest.setFamily("蚱蝉属");
            pest.setPlant("杨、柳、榆、女贞、竹、苦楝、水杉、悬铃木、桑、三叶橡胶、柚木及多种果树、山楂、樱花、枫杨、苹果");
            pest.setLatinName("Cryptotympana atrata Fabricius");
            pest.setArea("惠山区、滨湖区；赣榆区、连云区；泰兴、靖江；宿迁泗阳、沭阳、宿城区、宿豫区；射阳、盐都、大丰；镇江市；斜桥社区、苏州高新区、吴中区、常熟、昆山、吴江区、太仓；徐州市：云龙区、鼓楼区、泉山区、开发区、丰县、沛县、铜山区、睢宁县、邳州市、新沂市、贾汪区");
            pestObservableList.add(pest);
        }
    }

    public ObservableList<Pest> getPestObservableList() {
        return pestObservableList;
    }

    public OnRecyclerItemClickListener getLibraryItemClickListener() {
        return libraryItemClickListener;
    }

    public Pest getClickedPest() {
        return clickedPest;
    }
}
