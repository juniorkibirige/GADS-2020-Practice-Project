package lan.tmsystem.gadsleaderboard;

import java.util.ArrayList;
import java.util.List;

import lan.tmsystem.gadsleaderboard.models.Hours;
import lan.tmsystem.gadsleaderboard.models.SkillIq;

public class DataManager {
    private static DataManager ourInstance = null;
    private static String hours = "";
    private static String skilliq = "";
    private Boolean submissionSuccess = false;
    private List<Hours> mHours = new ArrayList<>();
    private List<SkillIq> mSkillIqs = new ArrayList<>();

    public static DataManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataManager();
        }
        return ourInstance;
    }

    public List<Hours> getHours() {
        return mHours;
    }

    public List<SkillIq> getSkillIqs() {
        return mSkillIqs;
    }

    public String getSkilliq() {
        return skilliq;
    }

    public void setSkilliq(String skilliq) {
        DataManager.skilliq = skilliq;
    }


    public String getData() {
        return hours;
    }

    public void setData(String data) {
        DataManager.hours = data;
    }

    public void addHours(Hours hours) {
        mHours.add(hours);
    }

    public void addSkillIq(SkillIq skillIq) {
        mSkillIqs.add(skillIq);
    }

    public Boolean getSubmissionSuccess() {
        return submissionSuccess;
    }

    public void setSubmissionSuccess(Boolean submissionSuccess) {
        this.submissionSuccess = submissionSuccess;
    }
}