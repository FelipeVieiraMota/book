package com.motafelipe.api.backoffice.models.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UploadedFileModel {
    private String name;
    private String location;
}
