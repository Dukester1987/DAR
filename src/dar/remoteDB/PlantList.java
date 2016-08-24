
package dar.remoteDB;

class PlantList {
    private String PlantNo;
    private String PlantDesc;
    private double Rate;

    public PlantList(String PlantNo, String PlantDesc, double Rate) {
        this.PlantNo = PlantNo;
        this.PlantDesc = PlantDesc;
        this.Rate = Rate;
    }

    public String getPlantNo() {
        return PlantNo;
    }

    public void setPlantNo(String PlantNo) {
        this.PlantNo = PlantNo;
    }

    public String getPlantDesc() {
        return PlantDesc;
    }

    public void setPlantDesc(String PlantDesc) {
        this.PlantDesc = PlantDesc;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double Rate) {
        this.Rate = Rate;
    }
    
    
    
}
