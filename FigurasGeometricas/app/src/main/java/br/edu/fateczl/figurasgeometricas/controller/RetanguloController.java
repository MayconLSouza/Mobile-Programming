package br.edu.fateczl.figurasgeometricas.controller;

import br.edu.fateczl.figurasgeometricas.model.Retangulo;

public class RetanguloController implements IGeometriaController<Retangulo> {
    @Override
    public float calcularArea(Retangulo retangulo) {
        return retangulo.getComprimento() * retangulo.getLargura();
    }

    @Override
    public float calcularPerimetro(Retangulo retangulo) {
        return (retangulo.getComprimento() + retangulo.getLargura()) * 2;
    }
}
