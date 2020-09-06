package lan.tmsystem.gadsleaderboard.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import lan.tmsystem.gadsleaderboard.DataManager;
import lan.tmsystem.gadsleaderboard.R;

public class Learner extends Fragment {
    RecyclerView list;
    DataManager mDataManager;

    public Learner() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learner, container, false);
        mDataManager = DataManager.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LearnerAdapter adapter = new LearnerAdapter(mDataManager.getHours(), getActivity());

        list = view.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }
}