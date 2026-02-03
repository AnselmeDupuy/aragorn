/**
 * Aragorn - Animations & Visual Effects
 * Dev Front 2 - Yassine
 */

document.addEventListener('DOMContentLoaded', function() {
    initFlashMessages();
    initScrollAnimations();
    initHealthBarEffects();
});

/**
 * Flash Messages - Auto dismiss after delay
 */
function initFlashMessages() {
    const flashMessages = document.querySelectorAll('.flash-message');

    flashMessages.forEach((message, index) => {
        // Stagger the appearance
        message.style.animationDelay = `${index * 0.1}s`;

        // Auto dismiss after 5 seconds
        setTimeout(() => {
            dismissFlashMessage(message);
        }, 5000 + (index * 500));
    });
}

function dismissFlashMessage(element) {
    element.classList.add('dismissing');
    setTimeout(() => {
        element.remove();
    }, 300);
}

/**
 * Show flash message programmatically
 */
function showFlashMessage(type, text) {
    const container = document.querySelector('.flash-container') || createFlashContainer();

    const message = document.createElement('div');
    message.className = `flash-message flash-${type} slide-in-down`;
    message.innerHTML = `
        <span class="flash-icon">${getFlashIcon(type)}</span>
        <span>${text}</span>
        <button class="flash-close" onclick="this.parentElement.remove()">&times;</button>
    `;

    container.appendChild(message);

    // Auto dismiss
    setTimeout(() => {
        dismissFlashMessage(message);
    }, 5000);
}

function createFlashContainer() {
    const container = document.createElement('div');
    container.className = 'flash-container';
    document.body.appendChild(container);
    return container;
}

function getFlashIcon(type) {
    const icons = {
        success: '&#10003;',
        error: '&#10007;',
        warning: '&#9888;',
        info: '&#8505;'
    };
    return icons[type] || icons.info;
}

/**
 * Scroll-triggered animations
 */
function initScrollAnimations() {
    const animatedElements = document.querySelectorAll('.fade-in, .slide-in-up, .slide-in-left, .slide-in-right');

    if ('IntersectionObserver' in window) {
        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.style.visibility = 'visible';
                    entry.target.classList.add('animated');
                    observer.unobserve(entry.target);
                }
            });
        }, {
            threshold: 0.1,
            rootMargin: '0px 0px -50px 0px'
        });

        animatedElements.forEach(el => {
            el.style.visibility = 'hidden';
            observer.observe(el);
        });
    } else {
        // Fallback for older browsers
        animatedElements.forEach(el => {
            el.style.visibility = 'visible';
        });
    }
}

/**
 * Health bar effects
 */
function initHealthBarEffects() {
    const healthBars = document.querySelectorAll('.health-fill');

    healthBars.forEach(bar => {
        checkHealthLevel(bar);

        // Observe changes
        const observer = new MutationObserver(() => {
            checkHealthLevel(bar);
        });

        observer.observe(bar, { attributes: true, attributeFilter: ['style'] });
    });
}

function checkHealthLevel(healthBar) {
    const width = parseFloat(healthBar.style.width) || 100;

    if (width <= 25) {
        healthBar.classList.add('low');
    } else {
        healthBar.classList.remove('low');
    }
}

/**
 * Battle animations
 */
function animateAttack(attackerElement, defenderElement) {
    // Attacker animation
    attackerElement.classList.add('attack-effect');

    setTimeout(() => {
        // Defender receives hit
        defenderElement.classList.add('shake');
        showDamageNumber(defenderElement, Math.floor(Math.random() * 50) + 10);

        setTimeout(() => {
            attackerElement.classList.remove('attack-effect');
            defenderElement.classList.remove('shake');
        }, 500);
    }, 200);
}

function showDamageNumber(targetElement, damage) {
    const damageEl = document.createElement('span');
    damageEl.className = 'damage-number';
    damageEl.textContent = `-${damage}`;

    const rect = targetElement.getBoundingClientRect();
    damageEl.style.left = `${rect.left + rect.width / 2}px`;
    damageEl.style.top = `${rect.top}px`;

    document.body.appendChild(damageEl);

    setTimeout(() => {
        damageEl.remove();
    }, 1000);
}

function animateCriticalHit(element) {
    element.classList.add('critical-hit');
    setTimeout(() => {
        element.classList.remove('critical-hit');
    }, 600);
}

function animateHeal(element) {
    element.classList.add('heal-effect');
    setTimeout(() => {
        element.classList.remove('heal-effect');
    }, 500);
}

/**
 * Card interactions
 */
function addCardHoverEffect(cardSelector) {
    const cards = document.querySelectorAll(cardSelector);

    cards.forEach(card => {
        card.addEventListener('mouseenter', () => {
            card.style.transform = 'translateY(-5px) scale(1.02)';
        });

        card.addEventListener('mouseleave', () => {
            card.style.transform = '';
        });
    });
}

/**
 * Loading spinner
 */
function showLoadingSpinner(container) {
    const spinner = document.createElement('div');
    spinner.className = 'loading-spinner';
    spinner.id = 'loading-spinner';
    container.appendChild(spinner);
}

function hideLoadingSpinner() {
    const spinner = document.getElementById('loading-spinner');
    if (spinner) {
        spinner.remove();
    }
}

/**
 * Utility: Add staggered animation to list items
 */
function staggerAnimation(selector, animationClass = 'fade-in') {
    const elements = document.querySelectorAll(selector);

    elements.forEach((el, index) => {
        el.style.animationDelay = `${index * 0.1}s`;
        el.classList.add(animationClass);
    });
}
