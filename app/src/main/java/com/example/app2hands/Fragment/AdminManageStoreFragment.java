package com.example.app2hands.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app2hands.Adapter.AdminProductAdapter;
import com.example.app2hands.Adapter.ProductAdapter;
import com.example.app2hands.Model.Product;
import com.example.app2hands.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminManageStoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminManageStoreFragment extends Fragment {
    RecyclerView rvAdminManageStore;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminManageStoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminManageStoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminManageStoreFragment newInstance(String param1, String param2) {
        AdminManageStoreFragment fragment = new AdminManageStoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_manage_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvAdminManageStore = view.findViewById(R.id.rvAdminManageStore);
        ArrayList<Product> products = (ArrayList<Product>) initData();

        AdminProductAdapter adapter = new AdminProductAdapter(products, getContext());

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);

        rvAdminManageStore.setAdapter(adapter);
        rvAdminManageStore.setLayoutManager(layoutManager);
    }

    public List<Product> initData(){
        String[] name = {"Iphone 14", "Iphone 14 Pro Max", "Samsung Galaxy ZFlip","ZFold3", "Alienware 17", "Zenphyrus G15", "Acer Triton"};
        String[] status = {"Còn tốt", "Cũ", "Mới", "Còn tốt", "Còn tốt", "Like new", "Còn tốt"};
        String[] price = {"1.200.000.000đ", "900k", "700k", "200k", "700k", "100k", "600k"};
        int[] img = {R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google};
        List<Product> productList = new ArrayList<>();
        for (int i=0; i<name.length;i++){
            Product p = new Product(img[i], name[i], price[i], status[i]);
            productList.add(p);
        }
        return productList;
    }
}