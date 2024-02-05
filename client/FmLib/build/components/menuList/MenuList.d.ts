import React, { FC } from 'react';
type MenuListItemProps = {
    title: string;
    active?: boolean;
    Icon?: React.ElementType;
    onClick?: () => void;
    subList?: React.ReactNode | React.ReactNode[];
};
type MenuListProps = {
    children: React.ReactNode | React.ReactNode[];
};
declare const MenuList: FC<MenuListProps>;
declare const SubMenuListItem: FC<MenuListItemProps>;
type ListItemProps = {
    hasSubList: boolean;
} & MenuListItemProps;
declare const MenuListItem: FC<ListItemProps>;
export { MenuList, MenuListItem, SubMenuListItem };
