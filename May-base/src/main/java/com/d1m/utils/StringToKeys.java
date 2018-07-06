package com.d1m.utils;

import org.openqa.selenium.Keys;

public final class StringToKeys {

	public static Keys stringToKeys(String key) {
		Keys keys = null;
		switch (key.toUpperCase()) {
		case "ENTER":
			keys = Keys.ENTER;
			break;
		case "TAB":
			keys = Keys.TAB;
			break;
		case "SHIFT":
			keys = Keys.SHIFT;
			break;
		case "CONTROL":
			keys = Keys.CONTROL;
			break;
		case "ALT":
			keys = Keys.ALT;
			break;
		case "NULL":
			keys = Keys.NULL;
			break;
		case "CANCEL":
			keys = Keys.CANCEL;
			break;
		case "HELP":
			keys = Keys.HELP;
			break;
		case "BACK_SPACE":
			keys = Keys.BACK_SPACE;
			break;
		case "CLEAR":
			keys = Keys.CLEAR;
			break;
		case "RETURN":
			keys = Keys.RETURN;
			break;
		case "LEFT_SHIFT":
			keys = Keys.LEFT_SHIFT;
			break;
		case "LEFT_CONTROL":
			keys = Keys.LEFT_CONTROL;
			break;
		case "LEFT_ALT":
			keys = Keys.LEFT_ALT;
			break;
		case "PAUSE":
			keys = Keys.PAUSE;
			break;
		case "ESCAPE":
			keys = Keys.ESCAPE;
			break;
		case "SPACE":
			keys = Keys.SPACE;
			break;
		case "PAGE_UP":
			keys = Keys.PAGE_UP;
			break;
		case "PAGE_DOWN":
			keys = Keys.PAGE_DOWN;
			break;
		case "END":
			keys = Keys.END;
			break;
		case "HOME":
			keys = Keys.HOME;
			break;
		case "LEFT":
			keys = Keys.LEFT;
			break;
		case "ARROW_LEFT":
			keys = Keys.ARROW_LEFT;
			break;
		case "UP":
			keys = Keys.UP;
			break;
		case "ARROW_UP":
			keys = Keys.ARROW_UP;
			break;
		case "DOWN":
			keys = Keys.DOWN;
			break;
		case "ARROW_DOWN":
			keys = Keys.ARROW_DOWN;
			break;
		case "INSERT":
			keys = Keys.INSERT;
			break;
		case "DELETE":
			keys = Keys.DELETE;
			break;
		case "SEMICOLON":
			keys = Keys.SEMICOLON;
			break;
		case "EQUALS":
			keys = Keys.EQUALS;
			break;
			
		// num Keys
		case "NUMPAD0":
			keys = Keys.NUMPAD0;
			break;
		case "NUMPAD1":
			keys = Keys.NUMPAD1;
			break;
		case "NUMPAD2":
			keys = Keys.NUMPAD2;
			break;
		case "NUMPAD3":
			keys = Keys.NUMPAD3;
			break;
		case "NUMPAD4":
			keys = Keys.NUMPAD4;
			break;
		case "NUMPAD5":
			keys = Keys.NUMPAD5;
			break;
		case "NUMPAD6":
			keys = Keys.NUMPAD6;
			break;
		case "NUMPAD7":
			keys = Keys.NUMPAD7;
			break;
		case "NUMPAD8":
			keys = Keys.NUMPAD8;
			break;
		case "NUMPAD9":
			keys = Keys.NUMPAD9;
			break;
		case "MULTIPLY":
			keys = Keys.MULTIPLY;
			break;
		case "ADD":
			keys = Keys.ADD;
			break;
		case "SEPARATOR":
			keys = Keys.SEPARATOR;
			break;
		case "SUBTRACT":
			keys = Keys.SUBTRACT;
			break;
		case "DECIMAL":
			keys = Keys.DECIMAL;
			break;
		case "DIVIDE":
			keys = Keys.DIVIDE;
			break;
			
		// Function Keys
		case "F1":
			keys = Keys.F1;
			break;
		case "F2":
			keys = Keys.F2;
			break;
		case "F3":
			keys = Keys.F3;
			break;
		case "F4":
			keys = Keys.F4;
			break;
		case "F5":
			keys = Keys.F5;
			break;
		case "F6":
			keys = Keys.F6;
			break;
		case "F7":
			keys = Keys.F7;
			break;
		case "F8":
			keys = Keys.F8;
			break;
		case "F9":
			keys = Keys.F9;
			break;
		case "F10":
			keys = Keys.F10;
			break;
		case "F11":
			keys = Keys.F11;
			break;
		case "F12":
			keys = Keys.F12;
			break;
			
		case "META":
			keys = Keys.META;
			break;
		case "COMMAND":
			keys = Keys.COMMAND;
			break;
		case "ZENKAKU_HANKAKU":
			keys = Keys.ZENKAKU_HANKAKU;
		}
		return keys;
	}
}
