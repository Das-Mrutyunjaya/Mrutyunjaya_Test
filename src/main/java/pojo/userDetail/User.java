package pojo.userDetail;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User{

	@JsonProperty("area")
	private String area;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("author")
	private String author;

	@JsonProperty("topics")
	private List<String> topics;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return area;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setTopics(List<String> topics){
		this.topics = topics;
	}

	public List<String> getTopics(){
		return topics;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"area = '" + area + '\'' + 
			",address = '" + address + '\'' + 
			",author = '" + author + '\'' + 
			",topics = '" + topics + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}