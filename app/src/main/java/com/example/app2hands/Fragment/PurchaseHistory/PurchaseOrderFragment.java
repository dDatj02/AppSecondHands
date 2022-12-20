package com.example.app2hands.Fragment.PurchaseHistory;

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

import com.example.app2hands.Adapter.ProductAdapter;
import com.example.app2hands.Adapter.PurchaseOrderAdapter;
import com.example.app2hands.Adapter.PurchaseViewPagerAdapter;
import com.example.app2hands.Model.Product;
import com.example.app2hands.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PurchaseOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PurchaseOrderFragment extends Fragment {
    RecyclerView rvPurchaseOrder;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PurchaseOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PurchaseOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PurchaseOrderFragment newInstance(String param1, String param2) {
        PurchaseOrderFragment fragment = new PurchaseOrderFragment();
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
        return inflater.inflate(R.layout.fragment_purchase_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPurchaseOrder = view.findViewById(R.id.rvPurchaseOrder);

        ArrayList<Product> products = (ArrayList<Product>) initData();

        PurchaseOrderAdapter adapter = new PurchaseOrderAdapter(products, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rvPurchaseOrder.setAdapter(adapter);
        rvPurchaseOrder.setLayoutManager(linearLayoutManager);
    }

    public List<Product> initData(){
        List<Product> productList = new ArrayList<>();
        return productList;
    }
}