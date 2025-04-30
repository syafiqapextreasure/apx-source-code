package com.ppk.topEntity.formsEntity;

import org.hibernate.annotations.ValueGenerationType;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class representing a proposal form.
 * Maps to the "proposal_form" table in the database.
 * Contains details about a proposal such as title, subject, material type, and more.
 * 
 */
@Entity
@Table(name = "proposal_form")
@Getter
@Setter
@ToString
public class ProposalFormEntity {

//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "proposal_id") // Use the actual column name in your DB if it's different
	  private Long id; // Unique identifier for the proposal

//  @Column(name = "proposal_id")
//  private Long proposalId;

  @Column(name = "title") // Removed the extra apostrophe from the column name
  private String title;

  @Column(name = "subject")
  private String subject;

  @Column(name = "material_type") // Added column annotation for consistency
  private String materialType;

  @Column(name = "isbn")
  private String ISBN;

  @Column(name = "author")
  private String author;

  @Column(name = "publisher")
  private String publisher;

  @Column(name = "edition")
  private String edition;

  @Column(name = "no_of_copies") // Added column annotation for consistency
  private String noOfCopies;

  @Column(name = "car_set") // Added column annotation for consistency
  private String carSet;

  @Column(name = "notes")
  private String notes;
}
