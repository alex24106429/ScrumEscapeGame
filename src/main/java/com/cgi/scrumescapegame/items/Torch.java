package com.cgi.scrumescapegame.items;

public class Torch extends Item {
	@Override
	public String getName() {
		return "Torch";
	}

	@Override
	public String getDescription() {
		return "Allows you to see in the dark.";
	}

	@Override
	public String getImagepath() {
		return "items/torch.png";
	}
	
	public Torch() {
		super(50);
	}
}
