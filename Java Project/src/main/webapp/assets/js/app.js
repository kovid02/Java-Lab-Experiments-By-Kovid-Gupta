(() => {
  const root = document.documentElement;
  const themeButton = document.querySelector('.theme-toggle');
  const themeIcon = document.querySelector('.theme-icon');
  const savedTheme = (() => { try { return localStorage.getItem('dream_facilities-theme'); } catch (_) { return null; } })();
  const setTheme = (theme) => {
    root.dataset.theme = theme;
    if (themeIcon) themeIcon.textContent = theme === 'dark' ? '☀' : '☾';
    if (themeButton) themeButton.setAttribute('aria-label', theme === 'dark' ? 'Switch to light mode' : 'Switch to dark mode');
    try { localStorage.setItem('dream_facilities-theme', theme); } catch (_) {}
  };
  setTheme(savedTheme === 'dark' ? 'dark' : 'light');
  themeButton?.addEventListener('click', () => {
    themeButton.animate([{ transform: 'rotate(0) scale(1)' }, { transform: 'rotate(150deg) scale(.85)' }, { transform: 'rotate(360deg) scale(1)' }], { duration: 420, easing: 'cubic-bezier(.2,.8,.2,1)' });
    setTheme(root.dataset.theme === 'dark' ? 'light' : 'dark');
  });
  const menu = document.querySelector('.mobile-menu');
  const nav = document.querySelector('.nav-links');
  menu?.addEventListener('click', () => { const open = nav.classList.toggle('open'); menu.setAttribute('aria-expanded', String(open)); menu.textContent = open ? '×' : '☰'; });
  document.querySelectorAll('.star-picker').forEach(picker => {
    const labels = [...picker.querySelectorAll('label')];
    const hint = picker.querySelector('.rating-hint');
    const descriptions = ['Needs improvement', 'Could be better', 'It was okay', 'Good experience', 'Excellent experience'];
    const paint = (n) => labels.forEach((label, i) => label.classList.toggle('selected', i < n));
    labels.forEach((label, i) => {
      label.addEventListener('mouseenter', () => paint(i + 1));
      label.addEventListener('click', () => { paint(i + 1); if (hint) hint.textContent = descriptions[i]; });
    });
    picker.addEventListener('mouseleave', () => { const selected = picker.querySelector('input:checked'); paint(selected ? Number(selected.value) : 0); });
    picker.querySelectorAll('input').forEach(input => input.addEventListener('change', () => { paint(Number(input.value)); if (hint) hint.textContent = descriptions[Number(input.value)-1]; }));
  });
  document.querySelectorAll('textarea[maxlength]').forEach(area => {
    const counter = area.parentElement.querySelector('.char-counter');
    const update = () => { if (counter) counter.textContent = `${area.value.length} / ${area.maxLength} characters`; };
    area.addEventListener('input', update); update();
  });
  document.querySelectorAll('.feedback-form[data-validate="feedback"]').forEach(form => form.addEventListener('submit', e => {
    if (!form.checkValidity()) { e.preventDefault(); form.reportValidity(); return; }
    const button = form.querySelector('.btn-submit');
    if (button) { button.disabled = true; button.querySelector('.button-label').textContent = 'Submitting...'; button.querySelector('.button-arrow').textContent = '◌'; button.classList.add('is-loading'); }
  }));
  const passwordToggle = document.querySelector('.password-toggle');
  passwordToggle?.addEventListener('click', () => { const input = document.querySelector('#adminPassword'); const visible = input.type === 'password'; input.type = visible ? 'text' : 'password'; passwordToggle.textContent = visible ? 'Hide' : 'Show'; });
  document.querySelectorAll('[data-confirm]').forEach(button => button.addEventListener('click', e => { if (!window.confirm(button.dataset.confirm)) e.preventDefault(); }));
  const counters = document.querySelectorAll('.count-up[data-count]');
  if ('IntersectionObserver' in window && counters.length) {
    const observer = new IntersectionObserver(entries => entries.forEach(entry => {
      if (!entry.isIntersecting) return;
      const el = entry.target; observer.unobserve(el); const target = Number(el.dataset.count) || 0; const duration = 850; const start = performance.now();
      const tick = now => { const p = Math.min(1, (now - start) / duration); const eased = 1 - Math.pow(1 - p, 4); el.textContent = Math.round(target * eased).toLocaleString(); if (p < 1) requestAnimationFrame(tick); };
      requestAnimationFrame(tick);
    }), { threshold: .5 });
    counters.forEach(el => observer.observe(el));
  }
  document.querySelectorAll('.action-card,.stat-card,.feedback-card').forEach(card => {
    card.addEventListener('pointermove', e => { if (window.matchMedia('(hover: hover)').matches) { const r = card.getBoundingClientRect(); card.style.setProperty('--pointer-x', `${e.clientX-r.left}px`); card.style.setProperty('--pointer-y', `${e.clientY-r.top}px`); } });
  });
})();
