package lan.tmsystem.gadsleaderboard.models;

import android.os.Parcel;
import android.os.Parcelable;

public final class Hours  implements Parcelable {
    private final String name;
    private final String hours;
    private final String country;
    private final String badgeUrl;

    public String getNames() {
        return name;
    }

    public String getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public Hours(String name, String hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    private Hours(Parcel source) {
        name = source.readString();
        hours = source.readString();
        country = source.readString();
        badgeUrl = source.readString();
    }

    public static final Parcelable.Creator<Hours> CREATOR = new Creator<Hours>() {
        @Override
        public Hours createFromParcel(Parcel in) {
            return new Hours(in);
        }

        @Override
        public Hours[] newArray(int size) {
            return new Hours[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(hours);
        dest.writeString(country);
        dest.writeString(badgeUrl);
    }
}
