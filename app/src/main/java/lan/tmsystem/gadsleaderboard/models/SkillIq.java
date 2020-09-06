package lan.tmsystem.gadsleaderboard.models;

import android.os.Parcel;
import android.os.Parcelable;

public final class SkillIq implements Parcelable {
    private final String name;
    private final String score;
    private final String country;
    private final String badgeUrl;

    public SkillIq(String name, String score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    protected SkillIq(Parcel in) {
        name = in.readString();
        score = in.readString();
        country = in.readString();
        badgeUrl = in.readString();
    }

    public static final Creator<SkillIq> CREATOR = new Creator<SkillIq>() {
        @Override
        public SkillIq createFromParcel(Parcel in) {
            return new SkillIq(in);
        }

        @Override
        public SkillIq[] newArray(int size) {
            return new SkillIq[size];
        }
    };

    public String getNames() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(score);
        dest.writeString(country);
        dest.writeString(badgeUrl);
    }
}