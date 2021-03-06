package com.example.basetemplate.model.po;

import com.example.basetemplate.util.jsonviews.PrivateView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPO {
	private Long id;
	private String username;
	@JsonView(PrivateView.class)
	private String password;
	private Boolean enabled;
}
