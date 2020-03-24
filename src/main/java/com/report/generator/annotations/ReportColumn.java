package com.report.generator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.CellType;

/**
 * Applied to a field to denote it corresponds to a particular column of a report
 * 
 * @author Christopher Oler
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ReportColumn {

    public String name();
    public CellType cellType() default CellType._NONE;

}
