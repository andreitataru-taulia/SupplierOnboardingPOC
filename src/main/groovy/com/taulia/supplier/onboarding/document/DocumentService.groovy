package com.taulia.supplier.onboarding.document

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class DocumentService {
    private final DocumentRepository documentRepository
}
