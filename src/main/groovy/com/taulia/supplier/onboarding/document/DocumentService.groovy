package com.taulia.supplier.onboarding.document

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DocumentService {
    private final DocumentRepository documentRepository

    @Autowired
    DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository
    }
}
