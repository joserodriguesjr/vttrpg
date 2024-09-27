package com.vttrpg.RPG.application.form;

import java.util.List;

public record CreateFieldForm(
        String name,
        List<Column> columns
) implements FieldForm {
    public record Column(
            String name,
            String type
    ) {}
}

//public class CreateFieldForm implements FieldForm {
//    String name;
//    //        List<Map<String,String>> columns
//    List<Field.Column> columns;
//}


