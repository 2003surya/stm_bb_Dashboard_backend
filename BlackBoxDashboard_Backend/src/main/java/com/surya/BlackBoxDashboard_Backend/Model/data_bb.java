package com.surya.BlackBoxDashboard_Backend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_bb")
public class data_bb {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Getter
    @Column(name = "gx")
    Double gx;

    @Column(name= "gy")
    Double gy;

    @Column(name= "gz")
    Double gz;

    @Column(name = "lat")
    String lat;

    @Column(name = "lon")
    String lon;

    @Column(name = "timestamp")
    LocalDateTime timestamp;

    @Column(name = "date")
    LocalDate date;


    @Override
    public String toString() {
        return "data_bb{" +
                "id=" + id +
                ", gx=" + gx +
                ", gy=" + gy +
                ", gz=" + gz +
                ", lat=" + lat +
                ", lon=" + lon +
                ", timestamp=" + timestamp +
                ", date=" + date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGx() {
        return gx;
    }

    public void setGx(Double gx) {
        this.gx = gx;
    }

    public Double getGy() {
        return gy;
    }

    public void setGy(Double gy) {
        this.gy = gy;
    }

    public Double getGz() {
        return gz;
    }

    public void setGz(Double gz) {
        this.gz = gz;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimestamp() {
        return timestamp.toString().substring(12,timestamp.toString().length()-1);
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
