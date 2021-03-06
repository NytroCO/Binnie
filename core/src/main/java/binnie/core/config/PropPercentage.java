package binnie.core.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import net.minecraftforge.common.config.Property;

@Retention(RetentionPolicy.RUNTIME)
@ConfigProperty.Type(propertyClass = PropPercentage.PropertyPercentage.class)
public @interface PropPercentage {
	int upper() default 100;

	int lower() default 0;

	class PropertyPercentage extends PropertyBase<Integer, PropPercentage> {
		public PropertyPercentage(final Field field, final BinnieConfiguration file, final ConfigProperty configProperty, final PropPercentage annotedProperty) throws IllegalArgumentException, IllegalAccessException {
			super(field, file, configProperty, annotedProperty);
		}

		@Override
		protected Integer getConfigValue() {
			return super.getProperty().getInt(this.getDefaultValue());
		}

		@Override
		protected void addComments() {
			this.addComment("Default value is " + this.getDefaultValue() + "%.");
			this.addComment("Range is " + this.getAnnotatedProperty().lower() + '-' + this.getAnnotatedProperty().upper() + "%.");
		}

		@Override
		protected Property getProperty() {
			return this.getFile().get(this.getCategory(), this.getKey(), this.getDefaultValue());
		}
	}
}
