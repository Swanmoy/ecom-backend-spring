package com.swanmoy.ecom.services.admin.faq;

import com.swanmoy.ecom.dto.FAQDto;

public interface FAQService {
    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
