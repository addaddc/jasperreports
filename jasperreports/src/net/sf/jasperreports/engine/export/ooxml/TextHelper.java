/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2009 JasperSoft Corporation http://www.jaspersoft.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 * JasperSoft Corporation
 * 539 Bryant Street, Suite 100
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.engine.export.ooxml;

import java.awt.Color;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.util.JRColorUtil;


/**
 * @author sanda zaharia (shertage@users.sourceforge.net)
 * @version $Id: StyleCache.java 2908 2009-07-21 14:32:01Z teodord $
 */
public class TextHelper
{
	/**
	 *
	 */
	private Writer styleWriter = null;//FIXMEDOCX rename this to docs writer
	private Map fontMap = null;
	private Set fontFaces = new HashSet();

	/**
	 *
	 */
	private Map textSpanStyles = new HashMap();
	private int textSpanStylesCounter = 0;


	/**
	 *
	 */
	public TextHelper(Writer styleWriter, Map fontMap)
	{
		this.styleWriter = styleWriter;
		this.fontMap = fontMap;
	}


	/**
	 *
	 */
	public Collection getFontFaces()
	{
		return fontFaces;
	}


//	/**
//	 *
//	 */
//	public void writeTextSpanStyle(Map attributes, String text) throws IOException
//	{
//		String fontFamily;
//		String fontFamilyAttr = (String)attributes.get(TextAttribute.FAMILY);
//		if (fontMap != null && fontMap.containsKey(fontFamilyAttr))
//		{
//			fontFamily = (String) fontMap.get(fontFamilyAttr);
//		}
//		else
//		{
//			fontFamily = fontFamilyAttr;
//		}
//		fontFaces.add(fontFamily);
//		
//		StringBuffer textSpanStyleIdBuffer = new StringBuffer();
//		textSpanStyleIdBuffer.append(fontFamily);
//
//		String forecolorHexa = null;
//		Color forecolor = (Color)attributes.get(TextAttribute.FOREGROUND);
//		if (!Color.black.equals(forecolor))
//		{
//			forecolorHexa = JRColorUtil.getColorHexa(forecolor);
//			textSpanStyleIdBuffer.append(forecolorHexa);
//		}
//
//		String backcolorHexa = null;
//		Color runBackcolor = (Color)attributes.get(TextAttribute.BACKGROUND);
//		if (runBackcolor != null)
//		{
//			backcolorHexa = JRColorUtil.getColorHexa(runBackcolor);
//			textSpanStyleIdBuffer.append(backcolorHexa);
//		}
//
//		String size = String.valueOf(attributes.get(TextAttribute.SIZE));
//		textSpanStyleIdBuffer.append(size);
//
//		String weight = null;
//		if (TextAttribute.WEIGHT_BOLD.equals(attributes.get(TextAttribute.WEIGHT)))
//		{
//			weight = "bold";
//			textSpanStyleIdBuffer.append(weight);
//		}
//		String posture = null;
//		if (TextAttribute.POSTURE_OBLIQUE.equals(attributes.get(TextAttribute.POSTURE)))
//		{
//			posture = "italic";
//			textSpanStyleIdBuffer.append(posture);
//		}
//		String underline = null;
//		if (TextAttribute.UNDERLINE_ON.equals(attributes.get(TextAttribute.UNDERLINE)))
//		{
//			underline = "single";
//			textSpanStyleIdBuffer.append(underline);
//		}
//		String strikeThrough = null;
//		if (TextAttribute.STRIKETHROUGH_ON.equals(attributes.get(TextAttribute.STRIKETHROUGH)))
//		{
//			strikeThrough = "single";
//			textSpanStyleIdBuffer.append(strikeThrough);
//		}
//
////		if (TextAttribute.SUPERSCRIPT_SUPER.equals(attributes.get(TextAttribute.SUPERSCRIPT)))
////		{
////			textSpanStyleIdBuffer.append(" vertical-align: super;");
////		}
////		else if (TextAttribute.SUPERSCRIPT_SUB.equals(attributes.get(TextAttribute.SUPERSCRIPT)))
////		{
////			textSpanStyleIdBuffer.append(" vertical-align: sub;");
////		}
//
//		String textSpanStyleId = textSpanStyleIdBuffer.toString();
//		String textSpanStyleName = (String)textSpanStyles.get(textSpanStyleId);
//		
//		if (textSpanStyleName == null)
//		{
//			textSpanStyleName = "T" + textSpanStylesCounter++;
//			textSpanStyles.put(textSpanStyleId, textSpanStyleName);
//			
//			styleWriter.write("<style:style style:name=\"" + textSpanStyleName + "\"");
//			styleWriter.write(" style:family=\"text\">\n");
//			styleWriter.write("<style:text-properties");
//			if (forecolorHexa != null)
//			{
//				styleWriter.write(" fo:color=\"#" + forecolorHexa+ "\"");
//			}
//			styleWriter.write(" style:font-name=\"" + fontFamily + "\"");
//			styleWriter.write(" fo:font-size=\"" + size + "pt\"");
//			if (posture != null)
//			{
//				styleWriter.write(" fo:font-style=\"" + posture + "\"");
//			}
//			if (weight != null)
//			{
//				styleWriter.write(" fo:font-weight=\"" + weight + "\"");
//			}
//			if (backcolorHexa != null)
//			{
//				styleWriter.write(" fo:background-color=\"#" + backcolorHexa + "\"");
//			}
////			styleWriter.write(" style:text-rotation-angle=\"" + textRotationAngle + "\"");
////			styleWriter.write(" style:text-rotation-scale=\"" + textRotationScale + "\"");
//			if (underline != null)
//			{
//				styleWriter.write(" style:text-underline-type=\"" + underline + "\"");
//			}
//			if (strikeThrough != null)
//			{
//				styleWriter.write(" style:text-line-through-type=\"" + strikeThrough + "\"");
//			}
//			styleWriter.write(">\n");
//			styleWriter.write("</style:text-properties>\n");	
//			styleWriter.write("</style:style>\n");
//		}
//	}

	/**
	 *
	 */
	public void writeTextSpanStyle(Map attributes, String text) throws IOException
	{
		String fontFamily;
		String fontFamilyAttr = (String)attributes.get(TextAttribute.FAMILY);
		if (fontMap != null && fontMap.containsKey(fontFamilyAttr))
		{
			fontFamily = (String) fontMap.get(fontFamilyAttr);
		}
		else
		{
			fontFamily = fontFamilyAttr;
		}
		fontFaces.add(fontFamily);
		
		StringBuffer textSpanStyleIdBuffer = new StringBuffer();
		textSpanStyleIdBuffer.append(fontFamily);

		String forecolorHexa = null;
		Color forecolor = (Color)attributes.get(TextAttribute.FOREGROUND);
		if (!Color.black.equals(forecolor))
		{
			forecolorHexa = JRColorUtil.getColorHexa(forecolor);
			textSpanStyleIdBuffer.append(forecolorHexa);
		}

		String backcolorHexa = null;
		Color runBackcolor = (Color)attributes.get(TextAttribute.BACKGROUND);
		if (runBackcolor != null)
		{
			backcolorHexa = JRColorUtil.getColorHexa(runBackcolor);
			textSpanStyleIdBuffer.append(backcolorHexa);
		}

		String size = String.valueOf(((Float)attributes.get(TextAttribute.SIZE)).floatValue() * 2);
		textSpanStyleIdBuffer.append(size);

		String weight = null;
		if (TextAttribute.WEIGHT_BOLD.equals(attributes.get(TextAttribute.WEIGHT)))
		{
			weight = "b";
			textSpanStyleIdBuffer.append(weight);
		}
		String posture = null;
		if (TextAttribute.POSTURE_OBLIQUE.equals(attributes.get(TextAttribute.POSTURE)))
		{
			posture = "i";
			textSpanStyleIdBuffer.append(posture);
		}
		String underline = null;
		if (TextAttribute.UNDERLINE_ON.equals(attributes.get(TextAttribute.UNDERLINE)))
		{
			underline = "u";
			textSpanStyleIdBuffer.append(underline);
		}
		String strikeThrough = null;
		if (TextAttribute.STRIKETHROUGH_ON.equals(attributes.get(TextAttribute.STRIKETHROUGH)))
		{
			strikeThrough = "s";
			textSpanStyleIdBuffer.append(strikeThrough);
		}

		String superscript = null;
		if (TextAttribute.SUPERSCRIPT_SUPER.equals(attributes.get(TextAttribute.SUPERSCRIPT)))
		{
			superscript = "super";
			textSpanStyleIdBuffer.append(superscript);
		}
		
		String subscript = null;
		if (TextAttribute.SUPERSCRIPT_SUB.equals(attributes.get(TextAttribute.SUPERSCRIPT)))
		{
			subscript = "sub";
			textSpanStyleIdBuffer.append(subscript);
		}

		String textSpanStyleId = textSpanStyleIdBuffer.toString();
		String textSpanStyleName = (String)textSpanStyles.get(textSpanStyleId);
		
		if (textSpanStyleName == null)
		{
			textSpanStyleName = "T" + textSpanStylesCounter++;
			textSpanStyles.put(textSpanStyleId, textSpanStyleName);
		}
		styleWriter.write("        <w:rFonts w:ascii=\"" + fontFamily + "\" /> \r\n");
		styleWriter.write("        <w:sz w:val=\"" + size + "\" /> \r\n");
		if (forecolorHexa != null)
		{
			styleWriter.write("        <w:color w:val=\"" + forecolorHexa + "\" /> \r\n");
		}
		if (backcolorHexa != null)
		{
			//FIXME: the highlight does not accept the color hexadecimal expression, but only few color names
//			bodyWriter.write("        <w:highlight w:val=\"" + backcolorHexa + "\" /> \r\n");
		}
		if (weight != null)
		{
			styleWriter.write("        <w:b /> \r\n");
		}
		if (posture != null)
		{
			styleWriter.write("        <w:i /> \r\n");
		}
		if (strikeThrough != null)
		{
			styleWriter.write("        <w:strike /> \r\n");
		}
		if (underline != null)
		{
			styleWriter.write("        <w:u w:val=\"single\"/> \r\n");
		}
		if (superscript != null)
		{
			styleWriter.write("        <w:vertAlign w:val=\"superscript\" /> \r\n");
		}
		if (subscript != null)
		{
			styleWriter.write("        <w:vertAlign w:val=\"subscript\" /> \r\n");
		}
		
//		bodyWriter.write("  </w:rPr> \r\n");
//		bodyWriter.write(" </w:style> \r\n");
		
	}

}

