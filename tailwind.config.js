const defaultTheme = require('tailwindcss/defaultTheme')

/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["./src/**/*.{html,js}"],
    theme: {
        fontFamily: {
            'sans': ['Geist', ...defaultTheme.fontFamily.sans],
        },
        extend: {},
    },
    plugins: [require('daisyui')],
}

