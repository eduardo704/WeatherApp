package model;

/**
 * Created by eduardo on 18/08/2015.
 */
public class Weather {
   private Condition condition=new Condition();
    private Place place= new Place();
    private double cloudPrecipitation;
    private double snowPrecipitation;
    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public double getCloudPrecipitation() {
        return cloudPrecipitation;
    }

    public void setCloudPrecipitation(double cloudPrecipitation) {
        this.cloudPrecipitation = cloudPrecipitation;
    }

    public double getSnowPrecipitation() {
        return snowPrecipitation;
    }

    public void setSnowPrecipitation(double snowPrecipitation) {
        this.snowPrecipitation = snowPrecipitation;
    }
}
