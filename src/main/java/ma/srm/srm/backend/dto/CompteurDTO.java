/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.srm.srm.backend.dto;

public class CompteurDTO {
    private Long id;
    private String type; // "eau" ou "electricite"
    private Double latitude;
    private Double longitude;

    public CompteurDTO(Long id, String type, Double latitude, Double longitude) {
        this.id = id;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}
