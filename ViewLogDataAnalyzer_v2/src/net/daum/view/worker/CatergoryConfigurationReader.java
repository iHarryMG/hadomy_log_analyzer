package net.daum.view.worker;

import net.daum.view.model.ViewCategory;

public interface CatergoryConfigurationReader {

	boolean readCategoryConfiguration(ViewCategory viewCategory, String string, String category);

}
