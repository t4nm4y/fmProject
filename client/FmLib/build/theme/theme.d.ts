import { InternalFmThemeType } from './colors/type';
export declare const getFmTheme: (mode?: 'light' | 'dark', theme?: InternalFmThemeType) => {
    spacing: number;
    palette: {
        primary: {
            main: any;
            light: any;
            dark: any;
        };
        secondary: {
            main: any;
            light: any;
            dark: any;
        };
        error: {
            main: any;
            light: any;
            dark: any;
        };
        success: {
            main: string;
        };
        input: {
            main: any;
        };
    };
    typography: {
        fontFamily: string;
        allVariants: {
            color: any;
        };
    };
    MuiCssBaseline: {
        styleOverrides: {
            html: {
                fontFamily: string;
            };
        };
    };
    breakpoints: {
        values: {
            mobileXS: number;
            mobileS: number;
            mobileM: number;
            mobileL: number;
            desktopMobile: number;
            tabletS: number;
            tablet: number;
            tabletM: number;
            tabletXL: number;
            laptop: number;
            laptopL: number;
            desktop: number;
        };
    };
};
