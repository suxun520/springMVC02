package conversion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 转换器，将页面传来的string类型转换为data类型
 * S:页面传来的类型
 * T：转换后的数据
 */
import org.springframework.core.convert.converter.Converter;

public class DateConvert implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		try {
			if (null != source) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.parse(source);
			}
		} catch (Exception e) {
		}
		return null;
	}

}
