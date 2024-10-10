package kr.co.subking.ingredients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomNameCount {
	private String ig_name;
	private int custom_count;
}
