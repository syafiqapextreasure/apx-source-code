package com.ppk.topEntity.formsEntity.dto;

//recommendation DTO Admin
public class MaterialProcurementProjection {
	private Long id;
	 private String email;
	    private String title;
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		private String author;

	    // Constructor
//	    public MaterialProcurementProjection(Long id, String email, String title, String author) {
//	       this.id=id;
//	    	this.email = email;
//	        this.title = title;
//	        this.author = author;
//	    }

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}
	    
}
