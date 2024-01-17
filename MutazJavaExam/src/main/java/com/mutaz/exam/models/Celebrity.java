package com.mutaz.exam.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Celebrities")
public class Celebrity {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotEmpty(message = " enter name")
		@Size(min = 3)
		private String name;
		

		@NotEmpty(message = "enter details ")
		@Size(min = 10)
		@Column(columnDefinition="TEXT")
		private String details;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
		private User user;
		
		@Column(updatable = false)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date createdAt;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date updatedAt;
		
		
		public Celebrity() {}
		
		
		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}

		@PreUpdate
		protected void onUpdate(){
		    this.updatedAt = new Date();
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


	


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getDetails() {
			return details;
		}


		public void setDetails(String details) {
			this.details = details;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public Date getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}


		public Date getUpdatedAt() {
			return updatedAt;
		}


		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

}
