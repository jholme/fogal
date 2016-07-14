package com.iii.fogal

public enum HomePageTile {
	TILE_01("tile_01"),
	TILE_02("tile_02"),
	TILE_03("tile_03"),
	TILE_04("tile_04"),
	TILE_05("tile_05"),
	TILE_06("tile_06"),
	TILE_07("tile_07"),
	TILE_08("tile_08"),
	TILE_09("tile_09"),
	TILE_10("tile_10")
	
	String cssId
	
	public HomePageTile(String cssId) {
		this.cssId = cssId
	}
	
	HomePageTile findByCssId(String css_id) {
		HomePageTile.findWhere(cssId:css_id)
	}
	
}
