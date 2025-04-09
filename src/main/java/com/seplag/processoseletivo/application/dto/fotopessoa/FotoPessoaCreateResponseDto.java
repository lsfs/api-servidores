package com.seplag.processoseletivo.application.dto.fotopessoa;

import java.util.List;

public record FotoPessoaCreateResponseDto(
        Long pessoa_id,
        List<FotoPessoaLinkResponse> fotos
) {
}
