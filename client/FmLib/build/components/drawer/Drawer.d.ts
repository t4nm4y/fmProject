import React from 'react';
type DrawerProps = {
    direction: 'top' | 'bottom' | 'left' | 'right';
    open: boolean;
    onClose?: () => void;
    children: React.ReactNode;
};
declare const Drawer: {
    (props: DrawerProps): import("@emotion/react/jsx-runtime").JSX.Element;
    defaultProps: {
        direction: string;
        open: boolean;
    };
};
export { Drawer };
