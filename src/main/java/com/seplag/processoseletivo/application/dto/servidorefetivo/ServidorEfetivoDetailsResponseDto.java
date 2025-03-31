package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.domain.model.ServidorEfetivo;

//TODO: adicionar fotografia
public record ServidorEfetivoDetailsResponseDto(
        String nome,
        int idade,
        UnidadeResponseDto unidade
) {
//    public static ServidorEfetivoResponseDto of(ServidorEfetivo servidorEfetivo) {
//        return new ServidorEfetivoResponseDto(
//                servidorEfetivo.getPessoa().getPes_nome(),
////                servidorEfetivo.getPessoa().getIdade(),
////                UnidadeResponseDto.of(servidorEfetivo.getLotacao())
//        );
    //}
}
