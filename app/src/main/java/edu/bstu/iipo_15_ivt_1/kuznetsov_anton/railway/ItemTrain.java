package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

/**
 * Created by user on 28.10.2015.
 */
public class ItemTrain {
    int number;
    String nameFrom;
    String nameTo;
    String timeStart;
    String timeFinish;
    String dateStart;
    String dateFinish;
    int carriage;
    int sit;

    public ItemTrain(int number, String nameFrom, String nameTo, String timeStart, String timeFinish, String dateStart, String dateFinish, int carriage, int sit) {
        this.number = number;
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.carriage = carriage;
        this.sit = sit;
    }
}
