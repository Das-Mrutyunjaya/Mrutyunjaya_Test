package pojo.userDetail;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address{

	@JsonProperty("country")
	private String country;

	@JsonProperty("city")
	private String city;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	@Override
 	public String toString(){
		return 
			"Address{" + 
			"country = '" + country + '\'' + 
			",city = '" + city + '\'' + 
			"}";
		}
}