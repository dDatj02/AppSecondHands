package com.example.app2hands.Fragment;

import android.content.Intent;
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
import android.widget.Button;

import com.example.app2hands.Adapter.ProductAdapter;
import com.example.app2hands.Adapter.PurchaseOrderAdapter;
import com.example.app2hands.AddNoti;
import com.example.app2hands.AddProduct;
import com.example.app2hands.Model.Product;
import com.example.app2hands.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreManageFragment extends Fragment {
    RecyclerView rvManageStore;
    Button btnAddProduct;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoreManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreManageFragment newInstance(String param1, String param2) {
        StoreManageFragment fragment = new StoreManageFragment();
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
        return inflater.inflate(R.layout.fragment_store_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvManageStore = view.findViewById(R.id.rvManageStore);

        ArrayList<Product> products = (ArrayList<Product>) initData();

        ProductAdapter adapter = new ProductAdapter(products, getContext());

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);

        rvManageStore.setAdapter(adapter);
        rvManageStore.setLayoutManager(layoutManager);

        btnAddProduct = view.findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddProduct.class);
                startActivity(intent);
            }
        });
    }

    public List<Product> initData(){
        List<Product> productList = new ArrayList<>();
        return productList;
    }
}