package com.gabrielluciano.core.entities;

import com.gabrielluciano.data.external.jpa.constraints.NotNumber;
import com.gabrielluciano.data.external.jpa.helpers.StringListConverter;
import com.gabrielluciano.data.external.jpa.helpers.UUIDGenerator;
import com.gabrielluciano.util.RegexPattern;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
@Cacheable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", type = UUIDGenerator.class)
    private UUID id;

    @Column(length = 32, unique = true, nullable = false)
    @NotBlank
    @Length(max = 32)
    @NotNumber
    private String apelido;

    @Column(length = 100, nullable = false)
    @NotBlank
    @Length(max = 100)
    @NotNumber
    private String nome;

    @Column(length = 10, nullable = false)
    @Pattern(regexp = RegexPattern.VALID_DATE)
    private String nascimento;

    @Convert(converter = StringListConverter.class)
    private List<@NotNumber String> stack = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa that = (Pessoa) o;
        return Objects.equals(apelido, that.apelido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apelido);
    }
}
