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

public class SkillIQ extends Fragment {
    RecyclerView list;
    DataManager mDataManager;

    public SkillIQ() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill_i_q, container, false);
        mDataManager = DataManager.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.list);

        SkillIQAdapter adapter = new SkillIQAdapter(mDataManager.getSkillIqs(), getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }
}