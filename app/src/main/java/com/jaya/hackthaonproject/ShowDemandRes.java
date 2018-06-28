package com.jaya.hackthaonproject;

/**
 * Created by shubham on 3/25/2017.
 */
public class ShowDemandRes {
    private String demand_id;

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(String demand_id) {
        this.demand_id = demand_id;
    }

    public String getNo_of_resources() {
        return no_of_resources;
    }

    public void setNo_of_resources(String no_of_resources) {
        this.no_of_resources = no_of_resources;
    }

    public String getCompletion_time() {
        return completion_time;
    }

    public void setCompletion_time(String completion_time) {
        this.completion_time = completion_time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }




    public String getDate_of_demand() {
        return date_of_demand;
    }

    public void setDate_of_demand(String date_of_demand) {
        this.date_of_demand = date_of_demand;
    }

    private String resource_type;
    private String no_of_resources;
    private String completion_time;
    private String priority;

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    private String location_id;
    private String date_of_demand;
    public ShowDemandRes(String demand_id, String resource_type, String no_of_resources, String completion_time, String priority, String location_id, String date_of_demand ) {
    this.demand_id=demand_id;
        this.resource_type=resource_type;
        this.no_of_resources=no_of_resources;
        this.completion_time=completion_time;
        this.priority=priority;
        this.location_id=location_id;
        this.date_of_demand=date_of_demand;
    }


}
