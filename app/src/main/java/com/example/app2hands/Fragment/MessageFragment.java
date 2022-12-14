package com.example.app2hands.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app2hands.Model.Message;
import com.example.app2hands.Adapter.MessageAdapter;
import com.example.app2hands.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {
    RecyclerView rvMessage;
    SearchView searchView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMessage = view.findViewById(R.id.rvMessage);
        searchView = view.findViewById(R.id.searchView);

        searchView.clearFocus();
        ArrayList<Message> messages = (ArrayList<Message>) initData();

        MessageAdapter adapter = new MessageAdapter(messages, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rvMessage.setAdapter(adapter);
        rvMessage.setLayoutManager(linearLayoutManager);
    }

    public List<Message> initData(){
        String[] name = {"Iphone 14", "Iphone 14 Pro Max", "Samsung Galaxy ZFlip","ZFold3", "Alienware 17", "Zenphyrus G15", "Acer Triton"};
        String[] msg = {"White", "Deep Purple", "Purple", "Olive Green", "Black", "Black", "Black"};
        int[] img = {R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google, R.drawable.ic_google};
        List<Message> messagesList = new ArrayList<>();
        for (int i=0; i<name.length;i++){
            Message p = new Message(img[i], name[i], msg[i]);
            messagesList.add(p);
        }
        return messagesList;
    }
}