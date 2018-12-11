package week1;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	public void filter(List<String> recipes, String filter) {
		List<String> filterRecipes = new ArrayList<>();
		String[] attrs = filter.split("::");
		
		
		for(String recipe : recipes) {
			List<String> filters = getFilters(recipe);
			
			for(int i=0; i<attrs.length; i++) {
				
				String[] text = attrs[i].split(":");
				
				String attr = text[0];
				
				String[] sub_attrs = text[1].split(",");
				
				for(String sub_attr : sub_attrs) {
					
				}
			}
		}
	}
	
	public List<String> getFilters(String recipe){
		List<String> filters = new ArrayList<>();
		Response recipeResponse = util.getRecipeDetail(recipe);
		filters.addAll(getNonNumericFilters(recipeResponse));
		filters.addAll(getNumericFilters(recipeResponse));
		return filters;
	}
	
	public List<String> getNonNumericFilters(Response res){
		List<String> filters = new ArrayList<String>();
		String[] attributes = {"occasion", "course", "cuisine"};
		for(int i=0; i<attributes.length; i++) {
			try {
				List<String> attributeValues = res.jsonPath().get("recipes[0]." + attributes[i] + ".ref");
				filters.addAll(attributeValues);
			}catch(NullPointerException e) {
				
			}
		}
		return filters;
	}
	
	public List<String> getNumericFilters(Response res){
		List<String> filters = new ArrayList<String>();
		try {
			String value = String.valueOf(util.getValueFromResponse(res, "recipes[0].cost_per_serving"));
			Double cost = convertStrToDouble(value);
			filters.add(getCostPerServingAttribute(cost));
		}catch(NullPointerException e) {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			String value = String.valueOf(util.getValueFromResponse(res, "recipes[0].ready_in"));
			int cost = convertStrToInteger(value);
			filters.add(getReadyInAttribute(cost));
		}catch(NullPointerException e) {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return filters;
	}
}
