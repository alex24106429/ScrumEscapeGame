package com.cgi.scrumescapegame.items;

public class Torch extends Item {
	@Override
	public String getName() {
		return "Fakkel";
	}

	@Override
	public String getDescription() {
		return "Laat je beter zien in het donker.";
	}

	@Override
	public String getImagepath() {
		return "items/torch.png";
	}
	
	public Torch() {
		super(50);
	}
}
