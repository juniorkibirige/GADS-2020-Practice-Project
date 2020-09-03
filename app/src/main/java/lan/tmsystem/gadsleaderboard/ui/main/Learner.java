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

import lan.tmsystem.gadsleaderboard.R;

public class Learner extends Fragment {
    RecyclerView list;

    public Learner() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learner, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList stringList = new ArrayList();

        stringList.add("Item 1A");
        stringList.add("Item 1B");
        stringList.add("Item 1C");
        stringList.add("Item 1D");
        stringList.add("Item 1E");
        stringList.add("Item 1F");
        stringList.add("Item 1G");
        stringList.add("Item 1H");
        stringList.add("Item 1I");
        stringList.add("Item 1J");
        stringList.add("Item 1K");
        stringList.add("Item 1L");
        stringList.add("Item 1M");
        stringList.add("Item 1N");
        stringList.add("Item 1O");
        stringList.add("Item 1P");
        stringList.add("Item 1Q");
        stringList.add("Item 1R");
        stringList.add("Item 1S");
        stringList.add("Item 1T");
        stringList.add("Item 1U");
        stringList.add("Item 1V");
        stringList.add("Item 1W");
        stringList.add("Item 1X");
        stringList.add("Item 1Y");
        stringList.add("Item 1Z");

        LearnerAdapter adapter = new LearnerAdapter(stringList, getActivity());

        list = (RecyclerView) view.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }
}