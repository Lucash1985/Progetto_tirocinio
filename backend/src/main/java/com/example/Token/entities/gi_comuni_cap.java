package com.example.Token.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="gi_comuni_cap")
public class gi_comuni_cap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "denominazione_ita", nullable = false)
private String denominazione_ita;
	@Column(name = "cap", nullable = false)
private	String cap;
	@Column(name = "sigla_provincia", nullable = false)
private String sigla_provincia;
	@Column(name = "denominazione_provincia", nullable = false)
private String denominazione_provincia;
	@Column(name = "denominazione_regione", nullable = false)
private	String denominazione_regione;
	@Column(name = "tipologia_regione", nullable = false)
private	String tipologia_regione;

}
