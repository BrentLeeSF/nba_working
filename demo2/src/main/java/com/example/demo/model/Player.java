package com.example.demo.model;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.*; // for Spring Boot 3


@Entity
@Immutable
@Table(name = "player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name="player_full_name")
	private String fullName;

	@Column(name="player_first_name")
	private String firstName;

	@Column(name="player_last_name")
	private String lastName;

	public Player() {

	}

	public Player(String fullName, String firstName, String lastName) {
		this.fullName = fullName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Player(String fullName) {
        this.fullName = fullName;
        String[] firstAndLastSplit = fullName.split(" ",2);
        this.firstName = firstAndLastSplit[0];
        this.lastName = firstAndLastSplit[1];
    }

	public long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getFirstName() {
		return firstName;
	}

    public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", fullName=" + fullName + ", desc=" + firstName + ", lastName=" + lastName + "]";
	}
}
