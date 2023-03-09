package com.example.demo.Notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used to help hold data in a note. It holds content as a String and checked status as a boolean
 * The checked status is only relivent when using ChecklistNote. In all other cases, checked status is not relevant
 * @author Nick Pehl, Cam Lettieri
 * 
 * 
 */
@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "textItems")
public class TextItem 
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean checked;
	
	private String contentString;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "test")
	private Note note;
	
	public boolean getChecked()
	{
		return checked;
	}
	
	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getContentString()
	{
		return contentString;
	}
	
	public void setContentString(String contentString)
	{
		this.contentString = contentString;
	}
	
	public Note getNote()
	{
		return note;
	}
	
	public void setNote(Note note)
	{
		this.note = note;
	}

}
