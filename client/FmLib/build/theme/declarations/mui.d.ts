import '@mui/material';
declare module '@mui/material' {
    interface BreakpointOverrides {
        xs: false;
        sm: false;
        md: false;
        lg: false;
        xl: false;
        mobileXS: true;
        mobileS: true;
        mobileM: true;
        mobileL: true;
        desktopMobile: true;
        tabletS: true;
        tablet: true;
        tabletM: true;
        tabletXL: true;
        laptop: true;
        laptopL: true;
        desktop: true;
    }
    interface Palette {
        input: Palette['primary'];
    }
    interface InputBasePropsColorOverrides {
        input: true;
    }
    interface TextFieldPropsColorOverrides {
        input: true;
    }
    interface FormLabelPropsColorOverrides {
        input: true;
    }
}
