package week1;

import java.util.List;

public class Sort {

	public boolean isSorted(List<String> recipes, String attribute, String sortOrder, CACRAPIUtility util) {
		attribute = recipeSortByMapping(attribute);
		if(sortOrder == null || sortOrder.isEmpty() || sortOrder.equalsIgnoreCase("desc"))
			return validateDescSort(recipes, attribute, util);
		return validateAscSort(recipes, attribute, util);
	}
	
	public boolean validateDescSort(List<String> recipes, String attribute, CACRAPIUtility util) {
		double max = Double.MAX_VALUE;
		for(String recipe : recipes) {
			double value = getAttributeValue(recipe, attribute, util);
			if(max >= value)
				max = value;
			else {
				s_logger.info("Recipe Id : {}", recipe);
				s_logger.info("compare {} vs {}", max, value);
				return false;
			}
		}
		return true;
	}
	
	public boolean validateAscSort(List<String> recipes, String attribute, CACRAPIUtility util) {
		double min = Double.MIN_VALUE;
		for(String recipe : recipes) {
			double value = getAttributeValue(recipe, attribute, util);
			if(min <= value)
				min = value;
			else
				return false;
		}
		return true;
	}
	
	public double getAttributeValue(String recipe, String attribute, CACRAPIUtility util) {
		Response res = util.getRecipeDetail(recipe);
		String value = "";
		String portionSize = "";
		Double exactValue = 0d;
		try {
			portionSize = String.valueOf(util.getValueFromResponse(res, "recipes[0].nutritional_details.PortionSize"));
			 if(attribute.equalsIgnoreCase("fat") || attribute.equalsIgnoreCase("saturatedfat") || attribute.equalsIgnoreCase("salt") || attribute.equalsIgnoreCase("sugar"))
				 value = String.valueOf(util.getValueFromResponse(res, "recipes[0].nutritional_information." + attribute + ".Amount"));
			 else 
				 value = String.valueOf(util.getValueFromResponse(res, "recipes[0]." + attribute));
		}catch(NullPointerException e) {
			
		}finally {
			Double portion = convertStrToDouble(portionSize);
			Double attributeValue = convertStrToDouble(value);
			exactValue = (attributeValue * AVG_PORTION_SIZE) / portion;
		}
		return exactValue;
	}
	
	public Double convertStrToDouble(String str) {
		str = str.replace("g", "");
		str = str.replace("ml", "");
		str = str.replace("p", "");
		str = str.replace("£", "");
		Double d = Double.parseDouble(str);
		return d;
	}
	
}
