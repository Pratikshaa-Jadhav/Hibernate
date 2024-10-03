package com.Anudip.hibernatedemo3;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Javaquestions")


public class Question {

	
		
		
			@Id
			 @GeneratedValue(strategy = GenerationType.IDENTITY)
			private int qid;
			@Column (name="Question_name")
			private String qname;
			@ElementCollection
		    @CollectionTable(name = "answers")
		    @Column(name = "answer")
			private List<String> answer;
			public int getQid() {
				return qid;
			}
			public void setQid(int qid) {
				this.qid = qid;
			}
			public String getQname() {
				return qname;
			}
			public void setQname(String qname) {
				this.qname = qname;
			}
			public List<String> getAnswer() {
				return answer;
			}
			public void setAnswer(List<String> answer) {
				this.answer = answer;
			}
			public Question() {
				
				// TODO Auto-generated constructor stub
			}
			

	

	}


